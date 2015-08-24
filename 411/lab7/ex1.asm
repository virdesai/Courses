#---------------------------------
# Lab 7: Gray Scale
#
# Name: Vir Desai
# Onyen: vird
#---------------------------------

.data 0x0
	newLine:	.asciiz "\n"
	pgmType:	.asciiz "P2\n"		#for pgm file

.text 0x3000
main:
	
	ori	$v0, $0, 8		#input string
	la	$a0, ($2)		#file type saved in location $2
	ori	$a1, $0, 2		#for the file type is length 3 always
	syscall
	
	ori	$v0, $0, 5		#input integer
	syscall
	add	$3, $v0, $0		#for column value
	
	ori	$v0, $0, 5		#input integer
	syscall
	add	$21, $v0, $0		#for row value
	
	ori	$v0, $0, 5		#input integer
	syscall
	add	$9, $v0, $0		#for ppm_max value
	
	ori	$v0, $0, 4
	la	$a0, pgmType
	syscall

	addi	$22, $0, 255		#set $1 equal to 255 for pgm_max
	addi	$20, $0, 3		#$20 equals 3

	ori	$v0, $0, 1
	la	$a0, ($3)
	syscall
	
	ori	$v0, $0, 4
	la	$a0, newLine
	syscall
	
	ori	$v0, $0, 1
	la	$a0, ($21)
	syscall
	
	ori	$v0, $0, 4
	la	$a0, newLine
	syscall
	
	ori	$v0, $0, 1
	la	$a0, ($22)
	syscall

loop:

	ori	$v0, $0, 4
	la	$a0, newLine
	syscall

	ori	$v0, $0, 5		#input integer
	syscall
	add	$10, $v0, $0		#for r value
	
	ori	$v0, $0, 5		#input integer
	syscall
	add	$11, $v0, $0		#for g value
	
	ori	$v0, $0, 5		#input integer
	syscall
	add	$12, $v0, $0		#for b value
	
	add	$13, $10, $11		#add r and g and store in $13
	add	$14, $12, $13		#add $13(r and g) with b and store in $14
	mult	$14, $22
	mflo	$15
	mult	$9, $20			#multiply ppm_max by 3 and put in $16
	mflo	$16
	div	$15, $16		#divide necessary info
	mflo	$17
	
	ori	$v0, $0, 1
	la	$a0, ($17)
	syscall
	
	addi	$8, $8, 1
	
break:
	blt	$8, $3, loop
	sub	$8, $8, $8
	addi	$7, $7, 1		#increment row index by 1
	blt	$7, $21, loop

exit:

	ori $v0, $0, 10			#System call code 10 for exit
	syscall				#exit the program
