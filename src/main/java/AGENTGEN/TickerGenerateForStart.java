package AGENTGEN;

import TIME.TimeUtil;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class TickerGenerateForStart extends OneShotBehaviour {
	private Agent agent;
	private AID[] links;
	private long hourDuration;
	private int[] gen;	
	public TickerGenerateForStart(Agent agent, AID[] links, int[] gen) {
		this.agent = agent;
		this.links =links;
		this.gen = gen;
	}

	@Override
	public void action() {

		
		//������� ����� � �����
		int timeH = TimeUtil.CurrentHour(); 
		//System.out.println(timeH);
		//������� ���������
		int genNow = gen[timeH];
		//���������� �������������� ���
		int countAPK = 0;
		for ( int i = 0; i < links.length; i++) {
			if (links[i] != null) {
				countAPK++;
			}
		}
		//������������ ��� ��������
		double sendGen = genNow/countAPK; 
		System.out.println(agent.getLocalName());
		System.out.println(sendGen);
		//�������� ������������ ��������
		ACLMessage loadToAPK= new ACLMessage(ACLMessage.INFORM);
		for (AID rec: links) {
			if (rec!=null) {
				loadToAPK.addReceiver(rec);
				loadToAPK.setProtocol("loadToAPK");
				loadToAPK.setContent(sendGen+"");
				agent.send(loadToAPK);
				loadToAPK.clearAllReceiver();
				
//				System.out.println(agent.getLocalName());
//				System.out.println(rec);
			}
			else {
				break;
			}
			
			
		}
		


	}

}
