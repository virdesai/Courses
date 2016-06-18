`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/08/2016 11:28:32 AM
// Design Name: 
// Module Name: debouncer
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

module debouncer #(parameter N=20)(
    input wire raw,
    input wire clk,
    input wire invert,
    output logic clean
    );

	logic [N-1:0] count;
	reg temp_clean = 0;

	always_ff @(posedge clk) begin
		count <= (raw != temp_clean) ? count + 1'b1 : 0;
		temp_clean <= (count[N-1] == 1'b1) ? raw : temp_clean;
	end
	
	assign clean = (temp_clean ^ invert);

endmodule

