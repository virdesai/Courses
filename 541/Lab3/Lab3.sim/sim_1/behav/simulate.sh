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
ExecStep $xv_path/bin/xsim Lab3_test_behav -key {Behavioral:sim_1:Functional:Lab3_test} -tclbatch Lab3_test.tcl -log simulate.log
