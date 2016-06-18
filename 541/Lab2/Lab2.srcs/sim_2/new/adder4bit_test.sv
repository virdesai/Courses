`timescale 1ns / 1ps
/////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 01/23/2016 12:10:12 PM
// Design Name: 
// Module Name: adder4bit_test
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


module adder4bit_test;

    logic [3:0] A;
    logic [3:0] B;
    logic Cin;
    wire [3:0] Sum;
    wire Cout;
    
    adder4bit uut(.A(A), .B(B), .Cin(Cin), .Sum(Sum), .Cout(Cout));
    
    initial begin
    // Initialize Inputs
        A = 0;
        B = 0;
        Cin = 0;
    // Wait, say, 100 ns before inputs start changing
        #100;
    
    // Add stimulus here
    // Inputs change every 10 ns
        #10 A=4'b0001; B=4'b0001; Cin=1'b0;
        #10 A=4'b1010; B=4'b0011; Cin=1'b0;
        #10 A=4'b1101; B=4'b1010; Cin=1'b1;
    end
    
    initial begin
        $monitor("time=",$time,, "A=%b B=%b Cin=%b : Sum=%b Cout=%b",A,B,Cin,Sum,Cout);
    end
    
endmodule