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
    output logic clean = 0
    );

	logic [N:0] count;

	always_ff @(posedge clk) begin
		count <= (raw != clean) ? count + 1'b1 : 0;
		clean <= (count[N] == 1'b1) ? raw : clean;
	end

endmodule

