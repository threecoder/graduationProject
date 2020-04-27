uni.onNetworkStatusChange(function(res) {
	console.log(res.isConnected);
	console.log(res.networkType);
});
export const getNetwork = () => {
	uni.getNetworkType({
		success(res) {
			let title = "当前WiFi环境为：" + res.networkType;
			uni.showToast({
				title: title,
				position: "center"
			});
		}
	})
}
