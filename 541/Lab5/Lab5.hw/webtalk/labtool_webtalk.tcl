webtalk_init -webtalk_dir /home/vir/Lab5/Lab5.hw/webtalk/
webtalk_register_client -client project
webtalk_add_data -client project -key date_generated -value "Fri Feb 19 10:44:59 2016" -context "software_version_and_target_device"
webtalk_add_data -client project -key product_version -value "Vivado v2015.4 (64-bit)" -context "software_version_and_target_device"
webtalk_add_data -client project -key build_version -value "1412921" -context "software_version_and_target_device"
webtalk_add_data -client project -key os_platform -value "LIN64" -context "software_version_and_target_device"
webtalk_add_data -client project -key registration_id -value "211142183_0_0_760" -context "software_version_and_target_device"
webtalk_add_data -client project -key tool_flow -value "labtool" -context "software_version_and_target_device"
webtalk_add_data -client project -key beta -value "FALSE" -context "software_version_and_target_device"
webtalk_add_data -client project -key route_design -value "FALSE" -context "software_version_and_target_device"
webtalk_add_data -client project -key target_family -value "not_applicable" -context "software_version_and_target_device"
webtalk_add_data -client project -key target_device -value "not_applicable" -context "software_version_and_target_device"
webtalk_add_data -client project -key target_package -value "not_applicable" -context "software_version_and_target_device"
webtalk_add_data -client project -key target_speed -value "not_applicable" -context "software_version_and_target_device"
webtalk_add_data -client project -key random_id -value "25ec550a-1128-4878-974a-93c929ef5504" -context "software_version_and_target_device"
webtalk_add_data -client project -key project_id -value "dbb44ab0-9b41-4ca6-ae55-754d659d68db" -context "software_version_and_target_device"
webtalk_add_data -client project -key project_iteration -value "2" -context "software_version_and_target_device"
webtalk_add_data -client project -key os_name -value "Ubuntu" -context "user_environment"
webtalk_add_data -client project -key os_release -value "Ubuntu 14.04.3 LTS" -context "user_environment"
webtalk_add_data -client project -key cpu_name -value "Intel(R) Core(TM) i7-3612QM CPU @ 2.10GHz" -context "user_environment"
webtalk_add_data -client project -key cpu_speed -value "2799.972 MHz" -context "user_environment"
webtalk_add_data -client project -key total_processors -value "1" -context "user_environment"
webtalk_add_data -client project -key system_ram -value "8.000 GB" -context "user_environment"
webtalk_register_client -client labtool
webtalk_add_data -client labtool -key chain -value "13631093" -context "labtool\\usage"
webtalk_add_data -client labtool -key pgmcnt -value "01:00:00" -context "labtool\\usage"
webtalk_add_data -client labtool -key cable -value "Digilent/Nexys4/15000000:" -context "labtool\\usage"
webtalk_transmit -clientid 2648298339 -regid "211142183_0_0_760" -xml /home/vir/Lab5/Lab5.hw/webtalk/usage_statistics_ext_labtool.xml -html /home/vir/Lab5/Lab5.hw/webtalk/usage_statistics_ext_labtool.html -wdm /home/vir/Lab5/Lab5.hw/webtalk/usage_statistics_ext_labtool.wdm -intro "<H3>LABTOOL Usage Report</H3><BR>"
webtalk_terminate
