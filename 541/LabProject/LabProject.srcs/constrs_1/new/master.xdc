## This file is a general .xdc for the Nexys4 rev B board
## To use it in a project:
## - uncomment the lines corresponding to used pins
## - rename the used ports (in each line, after get_ports) according to the top level signal names in the project

## Clock signal
## Bank = 35, Pin name = IO_L12P_T1_MRCC_35,				Sch name = CLK100MHZ
set_property PACKAGE_PIN E3 [get_ports clk]							
	set_property IOSTANDARD LVCMOS33 [get_ports clk]
	create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports clk]


#7 segment display
# Bank = 34, Pin name = IO_L2N_T0_34,						Sch name = CA
set_property PACKAGE_PIN L3 [get_ports {segments[7]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[7]}]
# Bank = 34, Pin name = IO_L3N_T0_DQS_34,					Sch name = CB
set_property PACKAGE_PIN N1 [get_ports {segments[6]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[6]}]
# Bank = 34, Pin name = IO_L6N_T0_VREF_34,					Sch name = CC
set_property PACKAGE_PIN L5 [get_ports {segments[5]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[5]}]
# Bank = 34, Pin name = IO_L5N_T0_34,						Sch name = CD
set_property PACKAGE_PIN L4 [get_ports {segments[4]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[4]}]
# Bank = 34, Pin name = IO_L2P_T0_34,						Sch name = CE
set_property PACKAGE_PIN K3 [get_ports {segments[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[3]}]
# Bank = 34, Pin name = IO_L4N_T0_34,						Sch name = CF
set_property PACKAGE_PIN M2 [get_ports {segments[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[2]}]
# Bank = 34, Pin name = IO_L6P_T0_34,						Sch name = CG
set_property PACKAGE_PIN L6 [get_ports {segments[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {segments[1]}]
# Bank = 34, Pin name = IO_L16P_T2_34,						Sch name = DP
set_property PACKAGE_PIN M4 [get_ports segments[0]]							
	set_property IOSTANDARD LVCMOS33 [get_ports segments[0]]

# Bank = 34, Pin name = IO_L18N_T2_34,						Sch name = AN0
set_property PACKAGE_PIN N6 [get_ports {digitselect[0]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[0]}]
# Bank = 34, Pin name = IO_L18P_T2_34,						Sch name = AN1
set_property PACKAGE_PIN M6 [get_ports {digitselect[1]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[1]}]
# Bank = 34, Pin name = IO_L4P_T0_34,						Sch name = AN2
set_property PACKAGE_PIN M3 [get_ports {digitselect[2]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[2]}]
# Bank = 34, Pin name = IO_L13_T2_MRCC_34,					Sch name = AN3
set_property PACKAGE_PIN N5 [get_ports {digitselect[3]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[3]}]
# Bank = 34, Pin name = IO_L3P_T0_DQS_34,					Sch name = AN4
set_property PACKAGE_PIN N2 [get_ports {digitselect[4]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[4]}]
# Bank = 34, Pin name = IO_L16N_T2_34,						Sch name = AN5
set_property PACKAGE_PIN N4 [get_ports {digitselect[5]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[5]}]
# Bank = 34, Pin name = IO_L1P_T0_34,						Sch name = AN6
set_property PACKAGE_PIN L1 [get_ports {digitselect[6]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[6]}]
# Bank = 34, Pin name = IO_L1N_T034,						Sch name = AN7
set_property PACKAGE_PIN M1 [get_ports {digitselect[7]}]					
	set_property IOSTANDARD LVCMOS33 [get_ports {digitselect[7]}]



##Buttons
## Bank = 15, Pin name = IO_L3P_T0_DQS_AD1P_15,			    Sch name = CPU_RESET
set_property PACKAGE_PIN C12 [get_ports reset]				
    set_property IOSTANDARD LVCMOS33 [get_ports reset]
## Bank = 15, Pin name = IO_L11N_T1_SRCC_15,				Sch name = BTNC
#set_property PACKAGE_PIN E16 [get_ports btnC]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnC]
## Bank = 15, Pin name = IO_L14P_T2_SRCC_15,				Sch name = BTNU
#set_property PACKAGE_PIN F15 [get_ports btnU]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnU]
## Bank = CONFIG, Pin name = IO_L15N_T2_DQS_DOUT_CSO_B_14,	Sch name = BTNL
#set_property PACKAGE_PIN T16 [get_ports btnL]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnL]
## Bank = 14, Pin name = IO_25_14,							Sch name = BTNR
#set_property PACKAGE_PIN R10 [get_ports btnR]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnR]
## Bank = 14, Pin name = IO_L21P_T3_DQS_14,					Sch name = BTND
#set_property PACKAGE_PIN V10 [get_ports btnD]						
	#set_property IOSTANDARD LVCMOS33 [get_ports btnD]


##VGA Connector
## Bank = 35, Pin name = IO_L8N_T1_AD14N_35,			    Sch name = VGA_R0
set_property PACKAGE_PIN A3 [get_ports {red[0]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {red[0]}]
## Bank = 35, Pin name = IO_L7N_T1_AD6N_35,					Sch name = VGA_R1
set_property PACKAGE_PIN B4 [get_ports {red[1]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {red[1]}]
## Bank = 35, Pin name = IO_L1N_T0_AD4N_35,					Sch name = VGA_R2
set_property PACKAGE_PIN C5 [get_ports {red[2]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {red[2]}]
## Bank = 35, Pin name = IO_L8P_T1_AD14P_35,				Sch name = VGA_R3
set_property PACKAGE_PIN A4 [get_ports {red[3]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {red[3]}]
## Bank = 35, Pin name = IO_L2P_T0_AD12P_35,				Sch name = VGA_B0
set_property PACKAGE_PIN B7 [get_ports {blue[0]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {blue[0]}]
## Bank = 35, Pin name = IO_L4N_T0_35,						Sch name = VGA_B1
set_property PACKAGE_PIN C7 [get_ports {blue[1]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {blue[1]}]
## Bank = 35, Pin name = IO_L6N_T0_VREF_35,					Sch name = VGA_B2
set_property PACKAGE_PIN D7 [get_ports {blue[2]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {blue[2]}]
## Bank = 35, Pin name = IO_L4P_T0_35,						Sch name = VGA_B3
set_property PACKAGE_PIN D8 [get_ports {blue[3]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {blue[3]}]
## Bank = 35, Pin name = IO_L1P_T0_AD4P_35,					Sch name = VGA_G0
set_property PACKAGE_PIN C6 [get_ports {green[0]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {green[0]}]
## Bank = 35, Pin name = IO_L3N_T0_DQS_AD5N_35,				Sch name = VGA_G1
set_property PACKAGE_PIN A5 [get_ports {green[1]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {green[1]}]
## Bank = 35, Pin name = IO_L2N_T0_AD12N_35,				Sch name = VGA_G2
set_property PACKAGE_PIN B6 [get_ports {green[2]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {green[2]}]
## Bank = 35, Pin name = IO_L3P_T0_DQS_AD5P_35,				Sch name = VGA_G3
set_property PACKAGE_PIN A6 [get_ports {green[3]}]				
    set_property IOSTANDARD LVCMOS33 [get_ports {green[3]}]
## Bank = 15, Pin name = IO_L4P_T0_15,						Sch name = VGA_HS
set_property PACKAGE_PIN B11 [get_ports hsync]						
    set_property IOSTANDARD LVCMOS33 [get_ports hsync]
## Bank = 15, Pin name = IO_L3N_T0_DQS_AD1N_15,				Sch name = VGA_VS
set_property PACKAGE_PIN B12 [get_ports vsync]						
    set_property IOSTANDARD LVCMOS33 [get_ports vsync]


##USB HID (PS/2)
## Bank = 35, Pin name = IO_L13P_T2_MRCC_35,				Sch name = PS2_CLK
set_property PACKAGE_PIN F4 [get_ports ps2_clk]						
	set_property IOSTANDARD LVCMOS33 [get_ports ps2_clk]
	set_property PULLUP true [get_ports ps2_clk]
## Bank = 35, Pin name = IO_L10N_T1_AD15N_35,				Sch name = PS2_DATA
set_property PACKAGE_PIN B2 [get_ports ps2_data]					
	set_property IOSTANDARD LVCMOS33 [get_ports ps2_data]	
	set_property PULLUP true [get_ports ps2_data]

	