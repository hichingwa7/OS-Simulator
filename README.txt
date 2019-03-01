--PROGRAM NAME: OS SIMULATOR

--Developer: Humphrey Ichingwa Shikoli

--Tags: Operating System, Data Structures, Algorithms, Threads

DESCRIPTION: Configuration files are are read into the simulation program and they specify the cycle times associated with each component, memory and any other necessary information required to run the simulation correctly. All cycle times are specified in milliseconds. A timer accurately displays timestamps for each OS operation. The metadata acts as the set of instructions for your simulation to run on. A process control block is used to update the state of the program. Memory is allocated for the program. Each input and output operation is completed by creating a new thread for the operation and waiting for it to complete. The threads are used for I/O operations to best simulate the hands-off role of the OS in controlling external devices.

--Files list-- 
1. Main.java
2. config.java
3. SimTimer.java
4. PCB.java
5. MetaDataQ.java
6. getCycleData.java
7. getConfigDatLOG.java
8. config_1.conf
9. config_2.conf
10. config_3.conf
11. config_4.conf
12. logfile_1.lgf
13. Test_3a.mdf
