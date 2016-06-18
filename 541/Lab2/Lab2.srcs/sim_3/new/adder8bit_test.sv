`timescale 1ns / 1ps
/////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 01/23/2016 1:00:24 PM
// Design Name: 
// Module Name: adder8bit_test
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


module adder8bit_test;

    logic [7:0] A;
    logic [7:0] B;
    logic Cin;
    wire [7:0] Sum;
    wire Cout;
    
    adder8bit uut(.A(A), .B(B), .Cin(Cin), .Sum(Sum), .Cout(Cout));
    
    initial begin
    // Initialize Inputs
        A = 0;
        B = 0;
        Cin = 0;
    // Wait, say, 100 ns before inputs start changing
        #100;
    
    // Add stimulus here
    // Inputs change every 10 ns
        #10 A=8'b00000001;B=8'b00000001;Cin=1'b0;
        #10 A=8'b00000001;B=8'b00000001;Cin=1'b1;
        #10 A=8'b00000010;B=8'b00000011;Cin=1'b0;
        #10 A=8'b10000001;B=8'b10000001;Cin=1'b0;
        #10 A=8'b00011001;B=8'b00110001;Cin=1'b0;
        #10 A=8'b00000011;B=8'b00000011;Cin=1'b1;
        #10 A=8'b11111111;B=8'b00000001;Cin=1'b0;
        #10 A=8'b11111111;B=8'b00000000;Cin=1'b1;
        #10 A=8'b11111111;B=8'b11111111;Cin=1'b0;
    end

    initial begin
        $monitor("time=",$time,, "A=%b B=%b Cin=%b : Sum=%b Cout=%b",A,B,Cin,Sum,Cout);
    end
    
endmodule