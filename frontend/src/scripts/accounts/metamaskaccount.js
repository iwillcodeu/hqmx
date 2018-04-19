window.addEventListener("load", function () {

    // Checking if Web3 has been injected by the browser (Mist/MetaMask)
    if (typeof web3 !== "undefined") {
        // Use Mist/MetaMask's provider
        window.web3 = new Web3(web3.currentProvider);
    } else {
        console.log("No web3? You should consider trying MetaMask!");
        // fallback - use your fallback strategy (local node / hosted node + in-dapp id mgmt / fail)
        window.web3 = new Web3(
            new Web3.providers.HttpProvider("https://localhost:8545")
        );
    }

    var account = "";
    web3.eth.getAccounts(function(error, result) {
        if (error) {
            console.error(error);
        } else {
            account = result[0].toString();
        }
    });
    var balance = 0
    web3.eth.getBalance(web3.eth.accounts[0], function(error, result) {
        if (error) {
            console.error(error);
        } else {
            balance = web3.fromWei(result.toNumber());
            balance = parseFloat(balance).toFixed(3);
            //alert("账户地址: " + account + "\n" + "账户余额: " + balance);
            console.log("账户地址: " + account + "\n" + "账户余额: " + balance)

            new Vue({
                el: '#getaccount',
                data: {
                    account: account,
                    balance: balance
                }
            })

        }
    });
    //startApp();

});

