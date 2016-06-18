//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/03/2016 12:39:07 PM
// Design Name: 
// Module Name: top
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
`include "display640x480.sv"

module top (
    input wire clk, reset, ps2_clk, ps2_data,
    output wire hsync, vsync,
    output wire [3:0] red, green, blue,
    output wire [7:0] segments, digitselect
);

   wire [31:0] pc, instr, mem_readdata, mem_writedata, mem_addr;
   wire mem_wr, c_reset;

   // Memory IO
   wire [`smemDataBits-1:0] charcode;
   wire [`smemAddrBits-1:0] smem_addr;
   wire clk100, clk50, clk25, clk12;

   clockdivider_Nexys4 clkdv(clk, clk100, clk50, clk25, clk12);
//   assign clk100=clk; assign clk50=clk; assign clk25=clk; assign clk12=clk;

   debouncer rbouncer(reset, clk12, 1, c_reset);

   mips mips(clk12, reset, pc, instr, mem_wr, mem_addr, mem_writedata, mem_readdata);

   imem imem(pc[31:0], instr);

   memIO memIO(clk12, ps2_clk, ps2_data, mem_wr, mem_addr, mem_writedata, mem_readdata, smem_addr, charcode, segments, digitselect);

   vgadisplaydriver display(clk100, charcode, hsync, vsync, red, green, blue,smem_addr);

endmodule
