`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/19/2016 07:42:41 PM
// Design Name: 
// Module Name: smem
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


`include "memory.sv"
`include "initfile.sv"

module smem (
    input wire clk, wr,
    input wire [`smemAddrBits-1:0] readaddr1, readaddr2, writeaddr,
    input wire [`smemDataBits-1:0] writedata,
    output wire [`smemDataBits-1:0] charcode1, charcode2
    );

    // Where screen memory is stored
    reg [`smemDataBits-1:0] mem [`smemLocation-1:0];
    initial $readmemh(`smem_init, mem, 0, `smemLocation - 1);
   
    // Memory write: only when wr==1, and only at posedge clock
    always_ff @(posedge clk)
        if(wr)
            mem[writeaddr] <= writedata; 
   
    // Returns the charcode which indexes bitmap memory
    assign charcode1 = mem[readaddr1];
    assign charcode2 = mem[readaddr2];

endmodule
