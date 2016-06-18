`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/19/2016 01:21:17 PM
// Design Name: 
// Module Name: counter8digit
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


module counter8digit(
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );
    
    logic [31:0] value;
    xcounter(clock, value);
    display8digit(value, clock, segments, digitselect);
    
endmodule
