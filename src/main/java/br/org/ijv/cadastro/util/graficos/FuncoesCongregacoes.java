package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.repository.Congregacoes;

@Named
@ViewScoped
public class FuncoesCongregacoes extends PessoaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Congregacoes congregacoes;
	
	private LineChartModel areaModel;
		
	public LineChartModel getAreaModel() {
		return areaModel;
	}

	@PostConstruct
	public void init() {
		initLinearModel();
		createAreaModel();
	}
	
	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		consultar();
		Congregacao congregacao = new Congregacao();
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			LineChartSeries congregacoes = new LineChartSeries();
			congregacoes.setLabel(congregacao.getNomeCongregacao());

			congregacoes.set("Membros", pessoas.membrosAtivosCongregacao(congregacao).size());
			congregacoes.set("Colaborador", pessoas.colaboradorCongregacao(congregacao).size());
			congregacoes.set("Intercessor", pessoas.intercessorCongregacao(congregacao).size());
			congregacoes.set("Louvor", pessoas.louvorCongregacao(congregacao).size());
			congregacoes.set("Diáconos", pessoas.diaconoCongregacao(congregacao).size());
			congregacoes.set("Pastores", pessoas.pastorCongregacao(congregacao).size());

			model.addSeries(congregacoes);
		}
		return model;
	}
	
	
	private void createAreaModel() {
		areaModel = new LineChartModel();

		LineChartSeries colaborador = new LineChartSeries();
		LineChartSeries membro = new LineChartSeries();
		LineChartSeries diacono = new LineChartSeries();
		LineChartSeries pastor = new LineChartSeries();
		LineChartSeries intercessor = new LineChartSeries();
		LineChartSeries louvor = new LineChartSeries();

		colaborador.setFill(true);
		colaborador.setLabel("Colaborador");

		membro.setFill(true);
		membro.setLabel("Membro");

		diacono.setFill(true);
		diacono.setLabel("Diácono");

		pastor.setFill(true);
		pastor.setLabel("Pastor");

		intercessor.setFill(true);
		intercessor.setLabel("Intercessor");

		louvor.setFill(true);
		louvor.setLabel("Louvor");

		consultar();
		Congregacao congregacao = new Congregacao();
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			colaborador.set(congregacao.getNomeCongregacao(), pessoas.colaboradorCongregacao(congregacao).size());
			membro.set(congregacao.getNomeCongregacao(), pessoas.membrosAtivosCongregacao(congregacao).size());
			diacono.set(congregacao.getNomeCongregacao(), pessoas.diaconoCongregacao(congregacao).size());
			pastor.set(congregacao.getNomeCongregacao(), pessoas.pastorCongregacao(congregacao).size());
			intercessor.set(congregacao.getNomeCongregacao(), pessoas.intercessorCongregacao(congregacao).size());
			louvor.set(congregacao.getNomeCongregacao(), pessoas.louvorCongregacao(congregacao).size());
		}

		areaModel.addSeries(membro);
		areaModel.addSeries(colaborador);
		areaModel.addSeries(louvor);
		areaModel.addSeries(intercessor);
		areaModel.addSeries(diacono);
		areaModel.addSeries(pastor);	
		areaModel.setStacked(true);

		Axis xAxis = new CategoryAxis("Congregações");
		areaModel.getAxes().put(AxisType.X, xAxis);
		
		
		areaModel.setTitle("Gráfico de funções nas congregações");
		
		areaModel.setAnimate(true);
		areaModel.setLegendPosition("ne");
		areaModel.setShowPointLabels(true);
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);
		areaModel.getAxes().put(AxisType.X, new CategoryAxis("Funções"));
		
		areaModel.getAxes().put(AxisType.X, xAxis);
		areaModel.setAnimate(true);
		areaModel.setLegendPosition("ne");
		
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Membros");
		yAxis.setMin(0);
		yAxis.getMax();
		yAxis.setMax(400);
	}

}
