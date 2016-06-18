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

module imem#(
    parameter Abits = 32,
    parameter Dbits = 32,
    parameter Nloc = 32,
    parameter imem_init = "sqr_imem.txt"
)(
    input wire [Dbits-1:0] pc,
    output wire [Dbits-1:0] instr
    );
    
    reg [Dbits-1:0] mem [Nloc-1:0];                  // The actual registers where data is stored
    initial $readmemh(imem_init, mem, 0, Nloc-1);    // Data to initialize registers
    assign instr = mem[pc[Dbits-1:2]];
        
endmodule
