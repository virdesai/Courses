`timescale 1ns / 1ps
`default_nettype none
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/05/2016 11:02:34 AM
// Design Name: 
// Module Name: CounterMod7
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments: Answer to question 2 on the lab: The value waveform is 
// shown as X for the first 2 nanoseconds because it was not initialized as 0 in
// the constructor of CounterMod7 like it was in CounterMod4. The value changes to
// 0 after that because the reset is high and the positive edge of the clock occurs
// and the logic for the clock positive edge with reset HIGH sets value as 0.
// CounterMod4 on the otherhand has the line "output logic [1:0] value = 0" which
// instantiates the value of value to 0 when the module is called so it never starts
// as X, it starts at 0.
// 
//////////////////////////////////////////////////////////////////////////////////


module CounterMod7(
    input wire clock,
    input wire reset,
    output logic [2:0] value
    );
    
    always_ff @(posedge clock) begin
        value <= reset ? 0 : (value == 6) ? 0 : (value+1);
    end

endmodule
