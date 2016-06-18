`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/08/2016 11:44:57 AM
// Design Name: 
// Module Name: tester_debounce
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


module tester_debounce();

    // Inputs to uut
    logic clock=0;
    logic rawX=0;

    // Outputs of uut
    wire cleanX;

    // Instantiate the Unit Under Test (UUT)
    debouncer #(4) uut (
        .raw(rawX),
        .clk(clock), 
        .clean(cleanX)
    );

    initial begin       // generate the clock
        forever begin   // Each clock cycle is 1ns (0.5 ns high and 0.5 ns low)
          #0.5 clock = ~clock;
          #0.5 clock = ~clock;
        end
    end
    
    initial begin
        #100 $finish;   // terminate simulation after 100 ns
    end

    integer i;
    initial begin
        #10;
        for(i=0; i<30; i++) begin
            #0.33 rawX = $urandom_range(0,1); 	// unsigned random number from 0 to 1
        end
        #0.33 rawX = 1;
        
        #30;
        for(i=0; i<30; i++) begin
            #0.33 rawX = $urandom_range(0,1);
        end
        #0.33 rawX = 0;
    end    

endmodule
