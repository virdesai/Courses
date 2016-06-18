//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/03/2016 03:48:34 PM
// Design Name: 
// Module Name: dmem
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

module dmem (
   input wire clock, mem_wr,
   input wire [`dmemAddrBits-1:0] mem_addr, 
   input wire [`dmemDataBits-1:0] mem_writedata,
   output wire [`dmemDataBits-1:0] mem_readdata
   );

   reg [`dmemDataBits-1:0] mem [`dmemLocation-1:0];                      // The actual registers where data is stored
   initial $readmemh(`dmem_init, mem, 0, `dmemLocation-1);        // Data to initialize registers

   always_ff @(posedge clock)                           // Memory write: only when wr==1, and only at posedge clock
         if(mem_wr)
            mem[{2'b00, mem_addr[`dmemDataBits-1:2]}] <= mem_writedata;

   assign mem_readdata = mem[{2'b00, mem_addr[`dmemDataBits-1:2]}];

endmodule
