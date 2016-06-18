`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/19/2016 10:54:20 AM
// Design Name: 
// Module Name: display4digit
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


module display4digit(
    input wire [15:0] val,
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );

	logic [31:0] count = 0;
	logic [1:0] toptwo;
	logic [3:0] value4bit;
	
	always_ff @(posedge clock)
		count <= count + 1'b1;
	
	assign toptwo[1:0] = count[18:17];
	// assign toptwo[1:0] = count[23:22];  // Try this instead to slow things down!

	
	assign digitselect[3:0] = ~ (  			// Note inversion
					   toptwo == 2'b00 ? 4'b0001  
				     : toptwo == 2'b01 ? 4'b0010
				     : toptwo == 2'b10 ? 4'b0100
				     : 4'b1000 );

	assign digitselect[7:4] = ~ 4'b0000;      // Since we are not using half of the display
		
	assign value4bit   =   (
				  toptwo == 2'b00 ? val[3:0]
				: toptwo == 2'b01 ? val[7:4]
				: toptwo == 2'b10 ? val[11:8]
				: val[15:12] );
	
	hexto7seg myhexencoder(value4bit, segments);

endmodule

