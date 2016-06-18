`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/19/2016 10:53:12 AM
// Design Name: 
// Module Name: counter4digit
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module counter4digit(
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );
    
    logic [15:0] value;
    xcounter(clock, value);
    display4digit(value, clock, segments, digitselect);
 
endmodule
