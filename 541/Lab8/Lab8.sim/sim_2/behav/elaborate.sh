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
ExecStep $xv_path/bin/xelab -wto 04264b19e6ce4ed4acd1a1f9a03b478f -m64 --debug typical --relax --mt 8 -L xil_defaultlib -L unisims_ver -L unimacro_ver -L secureip --snapshot displaydriver_10x4_test_behav xil_defaultlib.displaydriver_10x4_test xil_defaultlib.glbl -log elaborate.log
