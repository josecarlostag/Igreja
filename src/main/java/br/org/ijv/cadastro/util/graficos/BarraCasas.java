package br.org.ijv.cadastro.util.graficos;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.org.ijv.cadastro.controller.CasasBean;
import br.org.ijv.cadastro.model.Casa;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.repository.Casas;
import br.org.ijv.cadastro.repository.Congregacoes;


@Named
@ViewScoped
public class BarraCasas extends CasasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LineChartModel areaModel;
	
	private BarChartModel barModel;
	
	
	public BarChartModel getBarModel() {
		return barModel;
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}
	
	@Inject
	private Casas casas;
	
	@Inject
	private Congregacoes congregacoes;
	
	@PostConstruct
	public void init() {
		createAnimatedModels();
		createCongregacao();
	}
	
	private BarChartModel animatedModelBarra;
	
	private BarChartModel barraCongrega;
		
	public BarChartModel getBarraCongrega() {
		return barraCongrega;
	}

	public BarChartModel getAnimatedModelBarra() {
		return animatedModelBarra;
	}
	
	private void createAnimatedModels() {

		animatedModelBarra = initBarModel();
		animatedModelBarra.setTitle("Gráfico Casas de Benção e de Crescimento");

		animatedModelBarra.setShowPointLabels(true);
		animatedModelBarra.setAnimate(true);
		animatedModelBarra.setLegendPosition("ne");

		Axis yAxis = animatedModelBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Casas");
		yAxis.setMin(0);
		yAxis.setMax(80);
	}
	
	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
	
		consultar();
			
		ChartSeries bencao = new ChartSeries();
		bencao.setLabel("Casa de Benção");
		
		ChartSeries crescimento = new ChartSeries();
		crescimento.setLabel("Casa de Crescimento");
	
		bencao.set("Casa de Benção", casas.todasCasasBencaos().size());
		crescimento.set("Casa de Crescimento", casas.todasCasasCrescimento().size());
			
		model.addSeries(bencao);
		model.addSeries(crescimento);
	
		return model;
	}
	
	private void createCongregacao() {

		barraCongrega = initBarCongregacao();
		barraCongrega.setTitle("Gráfico de barras Casa de Benção e de Crescimento");

		barraCongrega.setShowPointLabels(true);
		barraCongrega.setAnimate(true);
		barraCongrega.setLegendPosition("ne");

		Axis yAxis = barraCongrega.getAxis(AxisType.Y);
		yAxis.setLabel("Casas");
		yAxis.setMin(0);
		yAxis.setMax(80);
	}
	
	private BarChartModel initBarCongregacao() {
		BarChartModel model = new BarChartModel();

		consultar();
		Casa casa = new Casa();
		Congregacao congregacao = new Congregacao();
		
		for (int j = 0; j < congregacoes.todasCongregacoes().size(); j++) {
			congregacao = congregacoes.todasCongregacoes().get(j);
			ChartSeries serieCasa = new ChartSeries();
			serieCasa.setLabel(congregacao.getNomeCongregacao());
			
		for (int i = 0; i < casas.todasCasas().size(); i++) {
			casa = casas.todasCasas().get(i);
			if(casa.getCongregacao() == congregacao){

			serieCasa.set("Casa de Benção", casas.todasCasasBencaos(congregacao).size());
			serieCasa.set("Casa de Crescimento", casas.todasCasasCrescimento(congregacao).size());
			
			}
		}
			model.addSeries(serieCasa);
			
		}
		
		return model;
	}

}

