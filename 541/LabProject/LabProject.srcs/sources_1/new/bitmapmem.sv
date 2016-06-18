`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/19/2016 06:57:48 PM
// Design Name: 
// Module Name: bitmapmem
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
`include "display640x480.sv"

module bitmapmem (
    input wire [`bmemAddrBits-1:0] addr,
    output logic [`bmemDataBits-1:0] color
    );

    // Where bitmap memory is stored
    reg [`bmemDataBits-1:0] mem [`bmemLocation-1:0];
    initial $readmemh(`bmem_init, mem, 0, `bmemLocation - 1);
    
    // Note the bitmap memory is readonly
    assign color = mem[addr];

endmodule
