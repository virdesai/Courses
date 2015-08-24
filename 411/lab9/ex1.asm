#---------------------------------------
# Lab : Computer Fibonacci numbers
#
# Name: Vir Desai
# Onyen: vird
#---------------------------------------

.data 0x0
	new:		.asciiz	"\n"

.text 0x3000
main:
	addi	$t7, $0, 1
	
loop:
	ori	$v0, $0, 5
	syscall
	add	$a0, $0, $v0

	jal	fibonacci

finish:
	add	$a0, $0, $v0
	ori	$v0, $0, 1
	syscall
	
	addu	$t2, $0, $a0
	
	ori	$v0, $0, 4
	la	$a0, new
	syscall
	
	addu	$a0, $0, $t2

	bnez	$a0, loop

	ori 	$v0, $0, 10		#System call code 10 for exit
	syscall				#exit the program

fibonacci:
	beq	$a0, 0, fib0
	beq	$a0, 1, fib1
	bgtu	$a0, 1, check

fib0:
	addiu	$v0, $0, 0
	jr	$ra
	
fib1:
	addiu	$v0, $0, 1
	jr	$ra
	
check:
	addiu	$sp, $sp, -400
	sw	$ra, 0($sp)
	sw	$a0, 4($sp)
	
	addiu	$a0, $a0, -1
	jal	fibonacci
	sw	$v0, 8($sp)
	
	lw	$a0, 4($sp)
	addiu	$a0, $a0, -2
	jal	fibonacci
	sw	$v0, 12($sp)
	
	lw	$ra, 0($sp)
	lw	$t0, 8($sp)
	lw	$t1, 12($sp)
	addiu	$sp, $sp, 400
	addu	$v0, $t0, $t1
	
	jr $ra