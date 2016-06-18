//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/03/2016 09:36:35 PM
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


`timescale 1ns / 1ps
`default_nettype none

module datapath #(
   parameter Abits = 5,          // Number of bits in address
   parameter Dbits = 32,         // Number of bits in data
   parameter Nloc = 32           // Number of memory locations
)(
    input wire clk,
    input wire reset,
    output reg [31:0] pc = 0,
    input wire [31:0] instr,
    input wire [1:0] pcsel, 
    input wire [1:0] wasel, 
    input wire [1:0] wdsel, 
    input wire [1:0] asel,
    input wire sext, bsel,
    input wire [4:0] alufn,
    input wire wr,
    input wire werf, 
    output wire Z,
    output logic [31:0] mem_addr,
    output logic [31:0] mem_writedata,
    input wire [31:0] mem_readdata
    );
    
    wire [4:0] Rs, Rt, Rd, shamt, reg_writeaddr;
    wire [25:0] J;
    wire [15:0] Imm;
    wire [31:0] signImm, aluA, aluB, alu_result, ReadData1, ReadData2, reg_writedata, PCPlus4, newpc, JT, BT;
    
    assign PCPlus4 = pc + 4;
    
    assign Rs = instr[25:21];
    assign Rt = instr[20:16];
    assign Rd = instr[15:11];
    
    assign J = instr[25:0];
    assign JT = ReadData1;
    assign BT = ((PCPlus4) + (signImm << 2));
    
    assign Imm = instr[15:0];
    assign signImm = {{16{sext & Imm[15]}},Imm};
    
    assign aluA = (asel == 2'b0) ? ReadData1 : (asel == 2'b01) ? {27'b0, instr[10:6]} : {27'b0, 5'b10000};
    assign aluB = (bsel == 1'b0) ? ReadData2 : signImm;
    
    always_ff @(posedge clk)
        pc <= (reset == 1'b1) ? 0 : newpc;
        
    assign newpc = (pcsel == 2'b0) ? PCPlus4 : (pcsel == 2'b01) ? BT : (pcsel == 2'b10) ? {pc[31:28], J, 2'b0} : JT;
    
    assign reg_writeaddr = (wasel == 2'b0) ? Rd : (wasel == 2'b01) ? Rt : (wasel == 2'b10) ? 5'b11111 : 5'bx;
    assign reg_writedata = (wdsel == 2'b0) ? PCPlus4 : (wdsel == 2'b01) ? alu_result : (wdsel == 2'b10) ? mem_readdata : 32'bx;
    
    assign mem_addr = alu_result;
    assign mem_writedata = ReadData2;
    
    register_file registers(clk, werf, Rs, Rt, reg_writeaddr, reg_writedata, ReadData1, ReadData2);
    ALU alu(aluA, aluB, alu_result, alufn, Z);
    
endmodule
