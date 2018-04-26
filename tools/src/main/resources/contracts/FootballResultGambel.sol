pragma solidity ^0.4.16;





/// @title duqiu
//缺少平台分钱的部分
//具体前转账还要细化
//函数的作用域，还要进一步考量
contract FootballResultGambel {

  uint[] public results;

  //组织者
  address public organizer;
  //平台地址
  address public platform;
  //投注者map（key->钱包地址,value->Supporter对象）
  mapping(address => uint) public supporterAmount;
  //投注者map（key->钱包地址,value->权重）
  mapping(address => uint) public supporterWeight;
  //结果和支持者的mapping
  mapping(uint => address[]) public resultSupporter;
  //结果和对应金额的mapping
  mapping(uint => uint) public resultAmount;
  address public sender;

  function FootballResultGambel(uint[] resultNames) public { // Constructor

    organizer=msg.sender;
    results=resultNames;
    for(uint i=0;i<results.length;i++)
    {
      resultAmount[results[i]]=0;
    }

    }




 //投注并算出权重
 //找到result对应的supporter数组
 //找到对应的supporter，然后计算权重
 function supportResult(uint _result, uint _amount) public
 {
   sender=msg.sender;
   supporterAmount[msg.sender]+=_amount;
   resultAmount[_result]+=_amount;
   address[] storage supporters= resultSupporter[_result];
   supporters.push(msg.sender);
   resultSupporter[_result]=supporters;

}


//根据权重给每个人发钱
function caculateWeight() public
{
    for(uint i=0;i<results.length;i++)
    {
      address[] storage supporters=resultSupporter[results[i]];
      for(uint j=0;j<supporters.length;j++)
      {
        //权重等于=个人投注金额 / 投注方总金额
        uint amount=supporterAmount[supporters[j]]*100;
        uint totalamount=resultAmount[results[i]];
        uint weight=amount/totalamount;
        supporterWeight[supporters[j]]=uint(weight);
      }
    }


}


//根据权重给每个人发钱
function addResult(uint _result) public
{

 uint winningAmount=0;

 //计算赢的钱
 for(uint j=0;j<results.length;j++)
 {
   if(results[j]==_result)
   {
     continue;
   }else{
     winningAmount+=resultAmount[results[j]];
     supporters=resultSupporter[results[j]];
     //把输家的钱清零
     for(uint k=0;k<supporters.length;k++)
     {
       supporterAmount[supporters[k]]=0;
     }
   }
 }

 //赢家分钱
  address[] storage supporters=resultSupporter[_result];
  for(uint i=0;i<supporters.length;i++)
  {
    supporterAmount[supporters[i]]+=uint(winningAmount*supporterWeight[supporters[i]]/100);

  }

}

//获取投注这的钱
function getAmount(address account) public constant returns (uint) {
    return supporterAmount[account];
}


//获取合约的钱
function deposit() public constant returns (uint){
  uint _amount= (address(this)).balance;
  return _amount;
}

}
