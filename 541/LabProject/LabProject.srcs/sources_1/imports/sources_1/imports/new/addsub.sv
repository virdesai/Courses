`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: Vir Desai
// 
// Create Date: 01/29/2016 10:54:54 AM
// Design Name: 
// Module Name: addsub
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


module addsub #(parameter N=32) (
    input wire [N-1:0] A, B,
    input wire Subtract,
    output wire [N-1:0] Result,
    output wire FlagN, FlagC, FlagV
    );
    
    wire [N-1:0] ToBornottoB = {N{Subtract}}^B;
    adder #(N) add(A, ToBornottoB, Subtract, Result, FlagN, FlagC, FlagV);
endmodule
