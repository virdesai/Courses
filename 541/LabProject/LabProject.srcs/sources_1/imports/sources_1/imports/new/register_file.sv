//////////////////////////////////////////////////////////////////////////////////
//
// Montek Singh
// 3/22/2016 
//
//////////////////////////////////////////////////////////////////////////////////

`timescale 1ns / 1ps
`default_nettype none

`include "initfile.sv"

module register_file #(
    parameter Abits = 5,
    parameter Dbits = 32,
    parameter Nloc = 32
)(
    input wire clock, wr,                                       // WriteEnable:  if wr==1, data is written into mem
    input wire [Abits-1 : 0] ReadAddr1, ReadAddr2, WriteAddr,   // 3 addresses
    input wire [Dbits-1 : 0] WriteData,                         // Data for writing into register file (if wr==1)
    output wire [Dbits-1 : 0] ReadData1, ReadData2              // 2 output ports
    );

    // Initialize Memory   
    reg [Dbits-1 : 0] rf [Nloc-1 : 0];
    initial $readmemh(`regd_init, rf, 0, Nloc - 1);

    // Memory write: only when wr==1, and only at posedge clock
    always_ff @(posedge clock)
        if(wr)
            rf[WriteAddr] <= WriteData;

    // When accessing register 0, always return 0
    assign ReadData1 = (ReadAddr1 == 0) ? 0 : rf[ReadAddr1];
    assign ReadData2 = (ReadAddr2 == 0) ? 0 : rf[ReadAddr2];
   
endmodule