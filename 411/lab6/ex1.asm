#---------------------------------
# Lab 5: Pixel Conversion
#
# Name: Vir Desai
# Onyen: vird
#
# --------------------------------
# Below is the expected output.
# 
# Converting pixels to grayscale:
# 0
# 1
# 2
# 34
# 5
# 67
# 89
# Finished.
# -- program is finished running --
#---------------------------------

.data 0x0
	startString:	.asciiz	"Converting pixels to grayscale:\n"
	finishString:	.asciiz	"Finished."
	newline:	.asciiz	"\n"
	pixels:		.word 	0x00010000,	0x010101,	0x6,		0x3333, 
				0x030c,		0x700853,	0x294999,	-1

.text 0x3000

main:
	ori	$v0, $0, 4			#System call code 4 for printing a string
	ori 	$a0, $0, 0x0   	 		#address of startString is in $a0
	syscall					#print the string
	sw	$0, 4($0)			#i = 0;
	lw	$7, 4($0)       		# allocate register for i
	addi	$1, $0, -1			#set $1 equal to -1

#------- INSERT YOUR CODE HERE -------

while:
	sll	$6, $7, 2			#sets $6 as the word register of i
	lw	$6, 48($6)			#loads into $6 the word at the register of word register $6
	beq	$6, $1, exit			#go to (exit) section if $8 is equal to -1
	srl 	$9, $6, 16			#shifts value at $6 right 4 bits and puts value in $9
	sll 	$6, $6, 16			#shifts value at $6 left 4 bits and makes it new value at $6
	srl 	$10, $6, 24			#shifts value at $6 right 6 bits and puts value in $10
	sll 	$6, $6, 8			#shifts value at $6 left 2 bits and makes it new value at $6
	srl 	$11, $6, 24			#shifts value at $6 right 6 bits and puts value in $11
	add 	$12, $9, $10			#add Red and Green values
	add 	$13, $11, $12			#add (Red and Green) with Blue value
	addi	 $14, $0, 3			#Makes $14 equal the number to divide by, 3
	div 	$15, $13, $14			#divide the added color values by 3 and store in $15
	li	$v0, 1				#print integer of $a0
	la	$a0, ($15)			#loads $a0 as value at $15
	syscall
	li	$v0, 4				#print a string
	la	$a0, newline			#load $a0 as the newline string call
	syscall
	addi	$7, $7, 1			#increment index i
	sw 	$7, 4($0)			#store new value
	beq 	$0, $0, while			# rerun through the loop

#
#(You may delete the comment here when you insert your code)
#
#------------ END CODE ---------------


exit:

	ori $v0, $0, 4				#System call code 4 for printing a string
	ori $a0, $0, 33  			#address of finishString is in $a0; we computed this
						#  simply by counting the number of chars in startString,
						#  including the \n and the terminating \0

	syscall					#print the string

	ori $v0, $0, 10				#System call code 10 for exit
	syscall					#exit the program
