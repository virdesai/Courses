`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 01/15/2016 10:38:33 AM
// Design Name: 
// Module Name: fulladder
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


module fulladder(
    input wire A,
    input wire B,
    input wire Cin,
    output wire Sum,
    output wire Carry_out
    );

    assign Sum = Cin ^ A ^ B;
    assign Carry_out = Cin & (A ^ B) | (A & B);

endmodule
