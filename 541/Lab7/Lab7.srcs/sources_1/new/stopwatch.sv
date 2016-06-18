`timescale 1ns / 1ps
`default_nettype none

module stopwatch(
    input wire BTNC,
    input wire BTNU,
    input wire BTND,
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );

    wire center, up, down;

    debouncer c(BTNC, clock, center);
    debouncer u(BTNU, clock, up);
    debouncer d(BTND, clock, down);

    wire countup, paused;

    fsm f(center, up, down, clock, countup, paused);

    logic [31:0] val;

    bcdcounter b(clock, countup, paused, val);

    display8digit e(val, clock, segments, digitselect);

endmodule
