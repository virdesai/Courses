`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/19/2016 11:13:34 AM
// Design Name: 
// Module Name: xcounter
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


module xcounter(
    input wire clock,
    output logic [31:0] value = 0
    );
    
    logic [50:0] temp = 0;
    
    always_ff @(posedge clock) begin
        temp <= temp + 1'b1;
        value <= temp[50:19];
    end

endmodule
