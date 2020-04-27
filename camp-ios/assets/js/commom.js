export const toast = data => {
    if (typeof data === 'string') {
        uni.showToast({
            title: data,
            position: "center"
        });
    } else {
        uni.showToast(data);
    }
}