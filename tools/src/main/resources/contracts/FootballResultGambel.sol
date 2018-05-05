pragma solidity ^0.4.16;





/// @title duqiu
//ȱ��ƽ̨��Ǯ�Ĳ���
//����ǰת�˻�Ҫϸ��
//�����������򣬻�Ҫ��һ������
contract FootballResultGambel {

  uint[] public results;

  //��֯��
  address public organizer;
  //ƽ̨��ַ
  address public platform;
  //Ͷע��map��key->Ǯ����ַ,value->Supporter����
  mapping(address => uint) public supporterAmount;
  //Ͷע��map��key->Ǯ����ַ,value->Ȩ�أ�
  mapping(address => uint) public supporterWeight;
  //�����֧���ߵ�mapping
  mapping(uint => address[]) public resultSupporter;
  //����Ͷ�Ӧ����mapping
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




 //Ͷע�����Ȩ��
 //�ҵ�result��Ӧ��supporter����
 //�ҵ���Ӧ��supporter��Ȼ�����Ȩ��
 function supportResult(uint _result, uint _amount) public
 {
   sender=msg.sender;
   supporterAmount[msg.sender]+=_amount;
   resultAmount[_result]+=_amount;
   address[] storage supporters= resultSupporter[_result];
   supporters.push(msg.sender);
   resultSupporter[_result]=supporters;

}


//����Ȩ�ظ�ÿ���˷�Ǯ
function caculateWeight() public
{
    for(uint i=0;i<results.length;i++)
    {
      address[] storage supporters=resultSupporter[results[i]];
      for(uint j=0;j<supporters.length;j++)
      {
        //Ȩ�ص���=����Ͷע��� / Ͷע���ܽ��
        uint amount=supporterAmount[supporters[j]]*100;
        uint totalamount=resultAmount[results[i]];
        uint weight=amount/totalamount;
        supporterWeight[supporters[j]]=uint(weight);
      }
    }


}


//����Ȩ�ظ�ÿ���˷�Ǯ
function addResult(uint _result) public
{

 uint winningAmount=0;

 //����Ӯ��Ǯ
 for(uint j=0;j<results.length;j++)
 {
   if(results[j]==_result)
   {
     continue;
   }else{
     winningAmount+=resultAmount[results[j]];
     supporters=resultSupporter[results[j]];
     //����ҵ�Ǯ����
     for(uint k=0;k<supporters.length;k++)
     {
       supporterAmount[supporters[k]]=0;
     }
   }
 }

 //Ӯ�ҷ�Ǯ
  address[] storage supporters=resultSupporter[_result];
  for(uint i=0;i<supporters.length;i++)
  {
    supporterAmount[supporters[i]]+=uint(winningAmount*supporterWeight[supporters[i]]/100);

  }

}

//��ȡͶע���Ǯ
function getAmount(address account) public constant returns (uint) {
    return supporterAmount[account];
}


//��ȡ��Լ��Ǯ
function deposit() public constant returns (uint){
  uint _amount= (address(this)).balance;
  return _amount;
}

}
