`timescale 1ns / 1ps
`default_nettype none
`include "display640x480.sv"
//////////////////////////////////////////////////////////////////////////////////
//
// Montek Singh
// 10/2/2015 
//
//////////////////////////////////////////////////////////////////////////////////


module vgadisplaydriver(
    input wire clk,
    output wire [3:0] red, green, blue,
    output wire hsync, vsync
    );

   wire [`xbits-1:0] x;
   wire [`ybits-1:0] y;
   wire activevideo;

   vgatimer myvgatimer(clk, hsync, vsync, activevideo, x, y);
   
   assign red[3:0]   = (activevideo == 1 && (x > `hSyncStart/8 && x < `hSyncStart/7 && y > `vSyncStart/8 && y < `vSyncStart/7) || (x > `hSyncStart/6 && x < `hSyncStart/5 && y > `vSyncStart/6 && y < `vSyncStart/5) || (x > `hSyncStart/4 && x < `hSyncStart/3 && y > `vSyncStart/4 && y < `vSyncStart/3) || (x > `hSyncStart/2 && x < `hSyncStart/1 && y > `vSyncStart/2 && y < `vSyncStart/1)) ? x[3:0] : 4'b0;
   assign green[3:0] = (activevideo == 1 && (x > `hSyncStart/8 && x < `hSyncStart/7 && y > `vSyncStart/8 && y < `vSyncStart/7) || (x > `hSyncStart/6 && x < `hSyncStart/5 && y > `vSyncStart/6 && y < `vSyncStart/5) || (x > `hSyncStart/4 && x < `hSyncStart/3 && y > `vSyncStart/4 && y < `vSyncStart/3) || (x > `hSyncStart/2 && x < `hSyncStart/1 && y > `vSyncStart/2 && y < `vSyncStart/1)) ? {x[2:1],y[1:0]} : 4'b0;
   assign blue[3:0]  = (activevideo == 1 && (x > `hSyncStart/8 && x < `hSyncStart/7 && y > `vSyncStart/8 && y < `vSyncStart/7) || (x > `hSyncStart/6 && x < `hSyncStart/5 && y > `vSyncStart/6 && y < `vSyncStart/5) || (x > `hSyncStart/4 && x < `hSyncStart/3 && y > `vSyncStart/4 && y < `vSyncStart/3) || (x > `hSyncStart/2 && x < `hSyncStart/1 && y > `vSyncStart/2 && y < `vSyncStart/1)) ? {y[2:0],1'b0} : 4'b0;

endmodule
