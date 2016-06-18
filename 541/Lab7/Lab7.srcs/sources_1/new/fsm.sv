`timescale 1ns / 1ps
`default_nettype none

module fsm(
    input wire center,
    input wire up,
    input wire down,
    input wire clk,
    output logic countup,
    output logic paused
    );

    enum { STATE0=3'b010, STATE1=3'b111, STATE2=3'b011, STATE3=3'b110, 
           STATE4=3'b000, STATE5=3'b101, STATE6=3'b001, STATE7=3'b100} state, next_state;

    always_ff @(posedge clk) begin
        state <= (countup == 1 | countup == 0) ? next_state : STATE0;
    end

    always_comb
        case (state)
            STATE0: next_state <= (center == 0 && down == 1 && paused == 0) ? STATE4 :
                                  (center == 1 && down == 0 && paused == 0) ? STATE1 : STATE0;
            STATE1: next_state <= (center == 1 && down == 0 && paused == 1) ? STATE1 : STATE2;
            STATE2: next_state <= (center == 0 && down == 1 && paused == 1) ? STATE6 :
                                  (center == 1 && down == 0 && paused == 1) ? STATE3 : STATE2;
            STATE3: next_state <= (center == 1 && down == 0 && paused == 0) ? STATE3 : STATE0;
            STATE4: next_state <= (center == 0 && up == 1 && paused == 0) ? STATE0 :
                                  (center == 1 && up == 0 && paused == 0) ? STATE5 : STATE4;
            STATE5: next_state <= (center == 1 && up == 0 && paused == 1) ? STATE5 : STATE6;
            STATE6: next_state <= (center == 0 && up == 1 && paused == 1) ? STATE2 :
                                  (center == 1 && up == 0 && paused == 1) ? STATE7 : STATE6;
            STATE7: next_state <= (center == 1 && up == 0 && paused == 0) ? STATE7 : STATE4;
            default: next_state <= STATE0;
        endcase

    always_comb
        case (state)
            STATE0: begin
                countup <= 1;
                paused <= 0;
                end
            STATE1: begin
                countup <= 1;
                paused <= 1;
                end
            STATE2: begin
                countup <= 1;
                paused <= 1;
                end
            STATE3: begin
                countup <= 1;
                paused <= 0;
                end
            STATE4: begin
                countup <= 0;
                paused <= 0;
                end
            STATE5: begin
                countup <= 0;
                paused <= 1;
                end
            STATE6: begin
                countup <= 0;
                paused <= 1;
                end
            STATE7: begin
                countup <= 0;
                paused <= 0;
                end
            default: begin
                countup <= 1;
                paused <= 0;
                end
        endcase
    
endmodule
