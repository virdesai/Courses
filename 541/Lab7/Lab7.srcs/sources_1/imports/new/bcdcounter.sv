`timescale 1ns / 1ps
`default_nettype none

module bcdcounter(
    input wire clock,
    input wire countup,
    input wire paused,
    output logic [31:0] val = 0
    );

	logic [19:0] count = 0;
	reg trigger = 0;
	
	always_ff @(posedge clock) begin
		count <= count + 1'b1;
		trigger <= count[19];
    end

    always_ff @(posedge trigger) begin
        if(~paused) begin
           if(countup) begin
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
           end else begin
              if(val[31:0] == 32'h00000000) begin
                  val[31:0] = 32'h99999999;
              end else if(val[27:0] == 28'h0000000) begin
                  val[27:0] = 28'h9999999;
                  val[31:28] = val[31:28] - 1'b1;
              end else if(val[23:0] == 24'h000000) begin
                  val[23:0] = 24'h999999;
                  val[27:24] = val[27:24] - 1'b1;
              end else if(val[19:0] == 20'h00000) begin
                  val[19:0] = 20'h99999;
                  val[23:20] = val[23:20] - 1'b1;
              end else if(val[15:0] == 16'h0000) begin
                  val[15:0] = 16'h9999;
                  val[19:16] = val[19:16] - 1'b1;
              end else if(val[11:0] == 12'h000) begin
                  val[11:0] = 12'h999;
                  val[15:12] = val[15:12] - 1'b1;
              end else if(val[7:0] == 8'h00) begin
                  val[7:0] = 8'h99;
                  val[11:8] = val[11:8] - 1'b1;
              end else if(val[3:0] == 4'h0) begin
                  val[3:0] = 4'h9;
                  val[7:4] = val[7:4] - 1'b1;
              end else begin
                  val[3:0] = val[3:0] - 1'b1;
              end
           end
        end
	end
	
endmodule
