#include<omp.h>
#include<stdio.h>
#include<iostream>
#include<unistd.h>
using namespace std;
int main()
{

int reader,writer=0;

cout<<"Enter the number of readers\n";
cin>>reader;
cout<<"Enter the number of writer\n";
cin>>writer;

//To check is reader can read
bool isreadable=true;

int shared_variable=-1;

//Declare lock
omp_lock_t my_lock;
omp_init_lock(&my_lock);

omp_set_num_threads(reader+writer);
#pragma omp parallel
{

int thread_num=omp_get_thread_num();

if(thread_num<reader)
	{
	//reader thread

	printf("Thread number : %4d  Reader is trying to read.\n",thread_num);
	while(isreadable==false)
		{
		printf("Thread number : %4d  Reader Resource not available.Going to sleep.\n",thread_num);
		sleep(1);
		}

	if(isreadable==true)
		{
		printf("Thread number : %4d  Reader read value%15d\n" ,thread_num, shared_variable);
		printf("Thread number : %4d  Reader leaving\n",thread_num);
		sleep(.50);

		}
	}//[END IF]
else
	{
	//writer thread
	printf("Thread number : %4d Writer Trying to write \n",thread_num);
	omp_set_lock(&my_lock);
	
	isreadable=false;
	printf("Thread number : %4d Writer acquried the lock\n",thread_num);
	sleep(1);
	shared_variable=thread_num;
	printf("Thread number : %4d Writer leaving\n",thread_num);
	isreadable=true;
	omp_unset_lock(&my_lock);
	}//[END ELSE]
}

return 0;
}

