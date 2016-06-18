`timescale 1ns / 1ps
`default_nettype none

module hexcounterdisplay(
    input wire clock,
    output logic [7:0] digitselect = ~(8'b0000_0001),
    output logic [7:0] segments
    );
    
    logic [3:0] value;
    counter1second(clock,value);
    hexto7seg(value,segments);
    
endmodule
