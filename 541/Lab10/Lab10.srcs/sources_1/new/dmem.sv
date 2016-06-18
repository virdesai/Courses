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

module dmem #(
   parameter Abits = 32,          // Number of bits in address
   parameter Dbits = 32,         // Number of bits in data
   parameter Nloc = 32,           // Number of memory locations
   parameter dmem_init = "sqr_dmem.txt"
)(
   input wire clock,
   input wire mem_wr,
   input wire [Abits-1:0] mem_addr,
   input wire [Dbits-1:0] mem_writedata,
   output wire [Dbits-1:0] mem_readdata
   );

   reg [Dbits-1:0] mem [Nloc-1:0];                      // The actual registers where data is stored
   initial $readmemh(dmem_init, mem, 0, Nloc-1);        // Data to initialize registers

   always_ff @(posedge clock)                           // Memory write: only when wr==1, and only at posedge clock
         mem[mem_addr[Dbits-1:2]] <= (mem_wr) ?  mem_writedata : mem[mem_addr[Dbits-1:2]];

   assign mem_readdata = mem[mem_addr[Dbits-1:2]];                              

endmodule
