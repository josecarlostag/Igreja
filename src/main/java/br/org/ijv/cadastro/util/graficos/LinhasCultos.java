package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.org.ijv.cadastro.controller.CultosBean;
import br.org.ijv.cadastro.model.Culto;
import br.org.ijv.cadastro.repository.Cultos;

@Named
@ViewScoped
public class LinhasCultos extends CultosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cultos cultos;
	
	
		
	private LineChartModel publicoCulto;
	
	
		
	public LineChartModel getPublicoCulto() {
		return publicoCulto;
	}
	

	
	private BarChartModel barModel;
		
	public BarChartModel getBarModel() {
		return barModel;
	}

	@PostConstruct
	public void init() {
		createAnimatedModels();				
	}
	

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	 private void createAnimatedModels() {

		 publicoCulto = initLinearModel();
		 publicoCulto.setTitle("Gráfico de frequência por categorias");
		 publicoCulto.setAnimate(true);
		 publicoCulto.setLegendPosition("ne");
		
		 publicoCulto.setShowPointLabels(true);
		 publicoCulto.getAxes().put(AxisType.X, new CategoryAxis("Datas"));
		 publicoCulto.setZoom(true);
		 Axis yAxis = publicoCulto.getAxis(AxisType.Y);
		 yAxis.setLabel("Publico");
		 yAxis.setMin(0);
		 yAxis.setMax(400);
		 DateAxis axis = new DateAxis("Datas");
		 
		 axis.setTickAngle(-50);
	     
	     axis.setTickFormat("%#d/%m/20%y");   
		 publicoCulto.getAxes().put(AxisType.X, axis);
	 }	 
				 
	 private LineChartModel initLinearModel() {
		 LineChartModel model = new LineChartModel();
		 		 
		 LineChartSeries pessoa = new LineChartSeries();
			
			pessoa.setLabel("Pessoas");
			
			LineChartSeries adolescente = new LineChartSeries();
		
			adolescente.setLabel("Adolescente");
			
			LineChartSeries preAdolescente = new LineChartSeries();
			
			preAdolescente.setLabel("Pre-adolescente");
			
			LineChartSeries crianca9 = new LineChartSeries();
			
			crianca9.setLabel("Criança(7-9)");
			
			LineChartSeries crianca6 = new LineChartSeries();
			
			crianca6.setLabel("Criança(5-6)");
			
			LineChartSeries crianca4 = new LineChartSeries();
			
			crianca4.setLabel("Criança(3-4)");
			
			LineChartSeries crianca2 = new LineChartSeries();
			
			crianca2.setLabel("Criança(0-2)");
			
			LineChartSeries consolidacao = new LineChartSeries();
			
			consolidacao.setLabel("Consolidação");
			
			LineChartSeries congregacao = new LineChartSeries();
			
			congregacao.setLabel("Congregação");
		 
		 consultar();
			Culto culto = new Culto();
			for (int i = 0; i < cultos.todosCultos().size(); i++) {
				culto = cultos.todosCultos().get(i);
			
			pessoa.set(culto.getDataReuniao().getTime(),culto.getQuantidadePessoas());	
			adolescente.set(culto.getDataReuniao().getTime(),culto.getQuantidadeAdolescente());
			preAdolescente.set(culto.getDataReuniao().getTime(),culto.getQuantidadePreAdolescente());
			crianca9.set(culto.getDataReuniao().getTime(),culto.getQuantidadeCriancas9());
			crianca6.set(culto.getDataReuniao().getTime(),culto.getQuantidadeCriancas6());
			crianca4.set(culto.getDataReuniao().getTime(),culto.getQuantidadeCriancas4());
			crianca2.set(culto.getDataReuniao().getTime(),culto.getQuantidadeCriancas2());
			consolidacao.set(culto.getDataReuniao().getTime(),culto.getConsolidacao());
			
			
			}
			model.addSeries(pessoa);
			model.addSeries(adolescente);
			model.addSeries(preAdolescente);
			model.addSeries(crianca9);
			model.addSeries(crianca6);
			model.addSeries(crianca4);
			model.addSeries(crianca2);
			model.addSeries(consolidacao);
			
			return model;
	 }
}
