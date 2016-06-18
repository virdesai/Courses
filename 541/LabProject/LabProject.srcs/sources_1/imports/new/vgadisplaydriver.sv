`timescale 1ns / 1ps
`default_nettype none
`include "memory.sv"
`include "display640x480.sv"
////////////////////////////////////////////////////////////////////////////////////
////
//// Montek Singh
//// 10/2/2015 
////
////////////////////////////////////////////////////////////////////////////////////


module vgadisplaydriver(
    input wire clk,
    input wire [`smemDataBits-1:0] charcode,
    output wire hsync, vsync,
    output wire [3:0] red, green, blue,
    output wire [`smemAddrBits-1:0] smem_addr
    );

   /*wire [`xbits-1:0] x, col;
   wire [`ybits-1:0] y, row;
   wire activevideo;
   vgatimer myvgatimer(clk, hsync, vsync, activevideo, x, y);
   
   assign row = {4'b0,y[`ybits-1:`ybits-4]};
   assign col = {4'b0,y[`xbits-1:`xbits-4]};       

   assign smem_addr = (row << 5) + (row << 3) + col;

   wire [`bmemDataBits-1:0] bmem_color;
   wire [`bmemAddrBits-1:0] bmem_addr;

   assign bmem_addr = {charcode, y[3:0], x[3:0]};
   bitmapmem bmem(bmem_addr, bmem_color);

   assign red[3:0]   = (activevideo == 1) ? bmem_color[11:8] : 4'b0;
   assign green[3:0] = (activevideo == 1) ? bmem_color[7:4] : 4'b0;
   assign blue[3:0]  = (activevideo == 1) ? bmem_color[3:0] : 4'b0;*/
   
   
       // VGA Display
       // ===========================================
       // Loop through all columns and rows of the VGA display
       
       wire [`xbits-1:0] x;
       wire [`ybits-1:0] y;
       wire activevideo;
       vgatimer timer(clk, hsync, vsync, activevideo, x, y);
       
       
       // Screen Memory
       // ===========================================
       // Note the screen address corresponds to the index of the character being
       // accessed, and not necessarily the (x, y) pixel coordinates we are looking at
       
       assign smem_addr = (x >> `charWidthShift) + ((y >> `charHeightShift) * `charColCount);
       
       
       // Bitmap Memory
       // ===========================================
       // The bitmap character returned is the offset of the character given
       // by the current (x, y) coordinate
       
       wire [`bmemDataBits-1:0] color;
       wire [`bmemAddrBits-1:0] bmem_addr;
       
       assign bmem_addr = (`charSize * charcode) + (x % `charWidth + ((y % `charHeight) * `charWidth));
       bitmapmem bmem(bmem_addr, color);
           
           
       // Display Adapter
       // ===========================================
       // Note the Nexys 4 VGA adapter requires 4 bits of data for each
       // color component.
       
       assign red[3:0]   = (activevideo == 1) ? color[11:8] : 4'b0;
       assign green[3:0] = (activevideo == 1) ? color[7:4] : 4'b0;
       assign blue[3:0]  = (activevideo == 1) ? color[3:0] : 4'b0;

endmodule
