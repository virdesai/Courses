//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/03/2016 10:01:06 PM
// Design Name: 
// Module Name: imem
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

`include "memory.sv"
`include "initfile.sv"

module imem (
    input wire [31:0] pc,
    output wire [31:0] instr
    );

    reg [`imemDataBits-1:0] mem [`imemLocation-1:0];;                  // The actual registers where data is stored
    initial $readmemh(`imem_init, mem, 0, `imemLocation-1);    // Data to initialize registers
    assign instr = mem[{2'b00, pc[31:2]}];

endmodule
