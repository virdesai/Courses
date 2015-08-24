#---------------------------------------
# Lab 8: ASCII Conversion and Factorial
#
# Name: Vir Desai
# Onyen: vird
#---------------------------------------

.data 0x0
	newLine:	.byte '\n'
	new:		.asciiz	"\n"
	endProgram:	.asciiz "0\n"
	buffer:		.space 21

.text 0x3000
main:
	addi	$t7, $0, 1		#set $t7 equal to 1 for initial ascii value
	lb	$t8, newLine		#set $t8 equal to "\n"
	la	$t9, endProgram 	#set $t9 equal to "0\n"
	add	$t6, $0, 20
	add	$t5, $0, 10
	
loop:
	ori	$v0, $0, 8
	la	$a0, buffer
	ori	$a1, $0, 20
	syscall
	
	ori	$s1, $0, 0		#i = 0
	ori	$s0, $0, 0		#instantiate $s0 as 0 before entering a_to_i
	
	jal	a_to_i
	addu	$s0, $0, $s0
	addu	$t1, $0, $s0

	jal	factorial
	addu	$t0, $0, $v0
	
	ori	$v0, $0, 1
	la	$a0, ($t0)
	syscall
	
	ori	$v0, $0, 4
	la	$a0, new
	syscall
	
	beqz	$t1, end
	bnez	$t1, loop

a_to_i:
	add	$t4, $s1, $a0
	lb	$s2, 0($t4)
	beq	$s2, $t8, return
	mult	$s0, $t5
	mflo	$s0
	add	$s0, $s0, $s2
	sub	$s0, $s0, 48

break1:
	beq	$s1, 20, return
	addi	$s1, $s1, 1		#increment string index by 1
	j	a_to_i

factorial:
	subu	$sp, $sp, 32
	sw	$ra, 20($sp)
	sw	$fp, 16($sp)
	addiu	$fp, $sp, 28
	sw	$s0, 0($fp)
	
	lw	$v0, 0($fp)
	bgtz	$v0, L2
	ori	$v0, $0, 1
	j L1

L2:
	lw	$v1, 0($fp)
	subu	$v0, $v1, 1
	move	$s0, $v0
	jal factorial
	
	lw	$v1, 0($fp)
	mulu	$v0, $v0, $v1

L1:
	lw	$ra, 20($sp)
	lw	$fp, 16($sp)
	addiu	$sp, $sp, 32
	jr	$ra

return:
	jr	$ra

end:
	ori $v0, $0, 10			#System call code 10 for exit
	syscall				#exit the program