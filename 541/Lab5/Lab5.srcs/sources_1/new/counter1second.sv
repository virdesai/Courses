`timescale 1ns / 1ps
`default_nettype none

module counter1second(
    input wire clock,
    output logic [3:0] value = 0
    );
    
    logic [31:0] temp = 0;
    
    always_ff @(posedge clock) begin
        temp <= temp + 1;
        value <= temp[29:26];
    end
    
endmodule
