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
ExecStep $xv_path/bin/xsim Lab9_test_behav -key {Behavioral:sim_1:Functional:Lab9_test} -tclbatch Lab9_test.tcl -log simulate.log
