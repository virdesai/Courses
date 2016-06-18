//////////////////////////////////////////////////////////////////////////////////
// Montek Singh
// 9/23/2015 
//////////////////////////////////////////////////////////////////////////////////

`timescale 1ns / 1ps
`default_nettype none

module seebounce(
    input wire [1:0] X,
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );

   logic [31:0] numBounces = 0;
   wire tempX;
   wire tempY;
   debouncer #(20) (X[0], clock, tempX);
   debouncer #(20) (X[1], clock, tempY);
   
//   always_ff @(posedge tempX)
//      numBounces <= numBounces + 1'b1;    // Let's count number of bounces

    bcdcounter (clock, tempX, tempY, numBounces);

   display8digit mydisplay(numBounces, clock, segments, digitselect);
		
endmodule
