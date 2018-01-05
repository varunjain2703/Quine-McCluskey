Quine-McCluskey Algorithm Implementation:
The Quine–McCluskey algorithm (or the method of prime implicants) is a method used for minimization of Boolean functions that was developed by Willard V. Quine and extended by Edward J. McCluskey.[1][2][3] It is functionally identical to Karnaugh mapping, but the tabular form makes it more efficient for use in computer algorithms, and it also gives a deterministic way to check that the minimal form of a Boolean function has been reached. It is sometimes referred to as the tabulation method.

Input description within input file located in src folder.

Sample input:
4	/*NUMBER OF VARRIABLES*/
6	/*NUMBER OF MIN-TERMS*/
2	/*NUMBER OF DONT CARE TERMS*/
4 8 10 11 12 15		/*LISTED MIN-TERMS*/
9 14				/*LISTED DONT CARE TERMS*/

Sample output:
f(A,B,C,D) = AC + AB' + BC'D'

Under guidance of Professor A. Daboli.

Algorithm reference:	https://en.wikipedia.org/wiki/Quine%E2%80%93McCluskey_algorithm
