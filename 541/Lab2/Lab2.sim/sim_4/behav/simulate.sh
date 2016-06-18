#!/bin/bash -f
xv_path="/opt/Xilinx/Vivado/2015.4"
ExecStep()
{
"$@"
RETVAL=$?
if [ $RETVAL -ne 0 ]
then
exit $RETVAL
fi
}
ExecStep $xv_path/bin/xsim addsub_test_behav -key {Behavioral:sim_4:Functional:addsub_test} -tclbatch addsub_test.tcl -log simulate.log
