Bloom Filter
Input: number of elements to be encoded, number of bits in the filter, number of hashes for demo, they are 1,000, 10,000 and 7, respectively
Function: generate 1,000 elements (denoted as set A) randomly, encode them in the filter, look up them in the filter, and generate another 1.000 elements randomly (denoted
as set B) and look up them in the filter.
Output: (1) the first line of the output: After lookup of elements in A, what is the number of elements you find in the filter?
(2) the second line of the output: After lookup of elements in B, what is the number of elements you find in the filter?

Counting Bloom Filter
Input: number of elements to be encoded initially, number of elements to be removed, number of elements to be added, number of counters in the filter, number of hashes –
for demo, they are 1,000, 500, 500, 10,000 and 7, respectively
Function: generate 1,000 elements (denoted A) randomly, encode them in the filter, remove 500 elements in A from the filter, add other 500 randomly generated elements in
the filter, and look up all original elements from A in the filter.
Output: After lookup of elements in A, what is the number of elements you find in the filter?

Coded Bloom filter
Input: number of sets, number of elements in each set, number of filters, number of bits in each filter, number of hashes – for demo, they are 7, 1000, 3, 30,000, and 7 respectively
Function: generate 7 sets of 1000 elements each, their codes are 001 through 111 respectively, encode all sets in 3 filters according to the algorithm, perform lookup on all
elements in the 7 sets 
Output: number of elements whose lookup results are correct
