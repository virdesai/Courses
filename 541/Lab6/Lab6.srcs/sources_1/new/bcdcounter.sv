`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/19/2016 01:36:51 PM
// Design Name: 
// Module Name: bcdcounter
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


module bcdcounter(
    //input wire [31:0] val,
    input wire clock,
    output logic [7:0] segments,
    output logic [7:0] digitselect
    );

    logic [31:0] val = 0;
	logic [25:0] count = 0;
	logic [2:0] topthree;
	logic [3:0] value4bit;
	reg trigger = 0;
	
	always_ff @(posedge clock) begin
		count <= count + 1'b1;
		trigger = count[25];
    end

	assign topthree[2:0] = count[18:16];
	// assign toptwo[1:0] = count[23:22];  // Try this instead to slow things down!
	
	assign digitselect[7:0] = ~ (  			// Note inversion
					   topthree == 3'b000 ? 8'b00000001  
				     : topthree == 3'b001 ? 8'b00000010
				     : topthree == 3'b010 ? 8'b00000100
				     : topthree == 3'b011 ? 8'b00001000
				     : topthree == 3'b100 ? 8'b00010000
				     : topthree == 3'b101 ? 8'b00100000
				     : topthree == 3'b110 ? 8'b01000000
				     : 8'b10000000 );
	
	always_ff @(posedge trigger) begin
	   if(val[31:0] == 32'h99999999) begin
	       val[31:0] = 32'b0;
	   end else if(val[27:0] == 28'h9999999) begin
	       val[27:0] = 28'b0;
	       val[31:28] = val[31:28] + 1'b1;
	   end else if(val[23:0] == 24'h999999) begin
	       val[23:0] = 24'b0;
	       val[27:24] = val[27:24] + 1'b1;
	   end else if(val[19:0] == 20'h99999) begin
	       val[19:0] = 20'b0;
	       val[23:20] = val[23:20] + 1'b1;
	   end else if(val[15:0] == 16'h9999) begin
	       val[15:0] = 16'b0;
	       val[19:16] = val[19:16] + 1'b1;
	   end else if(val[11:0] == 12'h999) begin
	       val[11:0] = 12'b0;
	       val[15:12] = val[15:12] + 1'b1;
	   end else if(val[7:0] == 8'h99) begin
	       val[7:0] = 8'b0;
	       val[11:8] = val[11:8] + 1'b1;
	   end else if(val[3:0] == 4'h9) begin
	       val[3:0] = 4'b0;
	       val[7:4] = val[7:4] + 1'b1;
	   end else begin
	       val[3:0] = val[3:0] + 1'b1;
	   end
	end
		
	assign value4bit   =   (
				  topthree == 3'b000 ? val[3:0] 
				: topthree == 3'b001 ? val[7:4]
				: topthree == 3'b010 ? val[11:8]
				: topthree == 3'b011 ? val[15:12]
				: topthree == 3'b100 ? val[19:16]
				: topthree == 3'b101 ? val[23:20]
				: topthree == 3'b110 ? val[27:24]
				: val[31:28] );
	
	hexto7seg myhexencoder(value4bit, segments);
	
endmodule
