`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/28/2016 11:58:50 AM
// Design Name: 
// Module Name: datapath
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


module datapath #(
    parameter Abits = 5,
    parameter Dbits = 4,
    parameter Nloc = 16
)(
    input wire clock,
    input wire RegWrite,
    input wire [$clog2(Nloc)-1 : 0] ReadAddr1, ReadAddr2, WriteAddr,
    input wire [4:0] ALUFN,
    input wire [Dbits-1 : 0] WriteData,
    output logic [Dbits-1 : 0] ReadData1, ReadData2, ALUResult,
    output wire FlagZ
    );
    
    register_file #(Nloc, Dbits) rf(clock, RegWrite, ReadAddr1, ReadAddr2, WriteAddr, WriteData, ReadData1, ReadData2);
    ALU #(Dbits) alu(ReadData1, ReadData2, ALUResult, ALUFN, FlagZ);
    
endmodule
