package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.org.ijv.cadastro.controller.CultosBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.Culto;
import br.org.ijv.cadastro.repository.Congregacoes;
import br.org.ijv.cadastro.repository.Cultos;

@Named
@ViewScoped
public class DataCultos extends CultosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Inject
	private Congregacoes congregacoes;
	
	@Inject
	private Cultos cultos;
	
	
	private LineChartModel animatedModelLinha;
	
	public LineChartModel getAnimatedModelLinha() {
		return animatedModelLinha;
	}
		
	@PostConstruct
	public void init() {
		createAnimatedModels();
		
	}
		
	private void createAnimatedModels() {

		animatedModelLinha = initLinearModel();
		animatedModelLinha.setTitle("Gráfico frequência por congregações");
		animatedModelLinha.setAnimate(true);
		animatedModelLinha.setLegendPosition("ne");
		animatedModelLinha.setShowPointLabels(true);
		animatedModelLinha.getAxes().put(AxisType.X, new CategoryAxis("Datas"));
		animatedModelLinha.setZoom(true);
		Axis yAxis = animatedModelLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Público");
		yAxis.setMin(0);
		yAxis.setMax(500);
		DateAxis axis = new DateAxis("Datas");
		// axis.setMax(); 
		 axis.setTickAngle(-50);
	     
	     axis.setTickFormat("%#d/%m/20%y");   
		 animatedModelLinha.getAxes().put(AxisType.X, axis);
	}
	
	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		consultar();
		Congregacao congregacao = new Congregacao();
		Culto culto = new Culto();
		
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			LineChartSeries serieCulto = new LineChartSeries();
			serieCulto.setLabel(congregacao.getNomeCongregacao());
			
			LineChartSeries serieConsolida = new LineChartSeries();
			serieConsolida.setLabel(congregacao.getNomeCongregacao());
			
			for (int j = 0; j < cultos.todosCultos().size(); j++) {
				culto = cultos.todosCultos().get(j);
				
					
					
			if(culto.getCongregacao() == congregacao)	{
				
			serieCulto.set(culto.getDataReuniao().getTime(), culto.getQuantidadePessoas());
			serieConsolida.set(culto.getDataReuniao().getTime(), culto.getConsolidacao());
			
			}
			
			}
			model.addSeries(serieCulto);
			
			
		}
		return model;
	}

}
