; Compute sum=158+number, where number is initialed to -105
.586
.MODEL FLAT

.STACK 4096

.DATA
number 	DWORD -105
number1 DWORD 12
sum	DWORD ?

.CODE
main	PROC
	mov	eax, number
	add	eax, 158
	add	eax, number1
	mov	sum, eax
	mov	eax, 0
	ret
main	ENDP

END
