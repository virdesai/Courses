#---------------------------------
# Lab 8: ASCII Conversion
#
# Name: Vir Desai
# Onyen: vird
#---------------------------------

.data 0x0
	newLine:	.byte '\n'
	new:		.asciiz	"\n"
	endProgram:	.asciiz "0\n"
	buffer:		.space 21
	
.text 0x3000
main:

	addiu	$t7, $0, 1		#set $t7 equal to 1 for initial ascii value
	lb	$t8, newLine		#set $t8 equal to "\n"
	la	$t9, endProgram 	#set $t9 equal to "0\n"
	addu	$t6, $0, 20
	addu	$t5, $0, 10
	
loop:
	ori	$v0, $0, 8
	la	$a0, buffer
	ori	$a1, $0, 20
	syscall
	
	ori	$s1, $0, 0		#i = 0
	ori	$s0, $0, 0		#instantiate $s0 as 0 before entering a_to_i
	
	jal	a_to_i
	la	$t0, ($s0)
	beqz	$t0, end
	
	ori	$v0, $0, 1
	la	$a0, ($t0)
	syscall
	
	ori	$v0, $0, 4
	la	$a0, new
	syscall

	bnez	$t0, loop

a_to_i:
	addu	$t4, $s1, $a0
	lb	$s2, 0($t4)
	beq	$s2, $t8, return
	multu	$s0, $t5
	mflo	$s0
	addu	$s0, $s0, $s2
	subu	$s0, $s0, 48
	
break:
	beq	$s1, 20, return
	addiu	$s1, $s1, 1		#increment string index by 1
	j	a_to_i
	
return:
	jr	$ra
	
end:
	ori $v0, $0, 10			#System call code 10 for exit
	syscall				#exit the program